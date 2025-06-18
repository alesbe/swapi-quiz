import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { PlanetsService, PlanetResponse } from '../../services/planets.service';
import { Planet } from '../shared/models/planet.model';
import { of, throwError } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, Location } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { PlanetsComponent } from '../planets/planets.component';

describe('PlanetsComponent', () => {
	let component: PlanetsComponent;
	let fixture: ComponentFixture<PlanetsComponent>;
	let planetsServiceSpy: jasmine.SpyObj<PlanetsService>;
	let locationSpy: jasmine.SpyObj<Location>;

	const mockPlanets: Planet[] = [
		{
			name: 'Tatooine',
			rotationPeriod: 23,
			orbitalPeriod: 304,
			diameter: 10465,
			climate: 'arid',
			gravity: '1 standard',
			terrain: 'desert',
			surfaceWater: 1,
			population: 200000,
			created: '2014-12-09T13:50:49.641000Z',
			edited: '2014-12-20T20:58:18.411000Z'
		}
	];

	const mockResponse: PlanetResponse = {
		content: mockPlanets,
		totalPages: 2,
		totalElements: mockPlanets.length,
		pageable: {
			pageNumber: 0,
			pageSize: mockPlanets.length
		}
	};

	beforeEach(async () => {
		planetsServiceSpy = jasmine.createSpyObj('PlanetsService', ['getPlanets']);
		locationSpy = jasmine.createSpyObj('Location', ['back']);

		await TestBed.configureTestingModule({
			imports: [PlanetsComponent, ReactiveFormsModule, CommonModule, HttpClientTestingModule],
			providers: [
				{ provide: PlanetsService, useValue: planetsServiceSpy },
				{ provide: Location, useValue: locationSpy }
			]
		}).compileComponents();

		fixture = TestBed.createComponent(PlanetsComponent);
		component = fixture.componentInstance;
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should load planets on init', () => {
		planetsServiceSpy.getPlanets.and.returnValue(of(mockResponse));
		component.ngOnInit();
		expect(planetsServiceSpy.getPlanets).toHaveBeenCalled();
		expect(component.planets.length).toBe(1);
		expect(component.totalPages).toBe(2);
		expect(component.loading).toBeFalse();
	});

	it('should handle error when loading planets', () => {
		planetsServiceSpy.getPlanets.and.returnValue(throwError(() => new Error('error')));
		component.loadPlanets();
		expect(component.planets).toEqual([]);
		expect(component.totalPages).toBe(1);
		expect(component.loading).toBeFalse();
	});

	it('should trigger loadPlanets on search input change', fakeAsync(() => {
		planetsServiceSpy.getPlanets.and.returnValue(of(mockResponse));
		component.ngOnInit();
		component.searchControl.setValue('Tat');
		tick(500);
		expect(planetsServiceSpy.getPlanets).toHaveBeenCalledTimes(2);
		expect(component.currentPage).toBe(0);
	}));

	it('should sort planets when onSort is called', () => {
		planetsServiceSpy.getPlanets.and.returnValue(of(mockResponse));
		component.onSort({ field: 'created', direction: 'desc' });
		expect(component.sortField).toBe('created');
		expect(component.sortDirection).toBe('desc');
		expect(component.currentPage).toBe(0);
		expect(planetsServiceSpy.getPlanets).toHaveBeenCalled();
	});

	it('should change page when onPageChange is called with valid page', () => {
		planetsServiceSpy.getPlanets.and.returnValue(of(mockResponse));
		component.totalPages = 2;
		component.onPageChange(1);
		expect(component.currentPage).toBe(1);
		expect(planetsServiceSpy.getPlanets).toHaveBeenCalled();
	});

	it('should not change page when onPageChange is called with invalid page', () => {
		planetsServiceSpy.getPlanets.and.returnValue(of(mockResponse));
		component.totalPages = 1;
		component.currentPage = 0;
		component.onPageChange(-1);
		expect(component.currentPage).toBe(0);
		component.onPageChange(2);
		expect(component.currentPage).toBe(0);
	});

	it('getValue should return the correct field value', () => {
		const planet = mockPlanets[0];
		expect(component.getValue(planet, 'name')).toBe('Tatooine');
	});

	it('sanitizeValue should return correct sanitized values', () => {
		expect(component.sanitizeValue(null)).toBe('Unknown');
		expect(component.sanitizeValue('unknown')).toBe('Unknown');
		expect(component.sanitizeValue('n/a')).toBe('-');
		expect(component.sanitizeValue('desert')).toBe('');
	});

	it('formatValue should format values correctly', () => {
		expect(component.formatValue(null, 'name')).toBe('*Unknown*');
		expect(component.formatValue('unknown', 'name')).toBe('*Unknown*');
		expect(component.formatValue('n/a', 'name')).toBe('-');
		expect(component.formatValue('2014-12-09T13:50:49.641000Z', 'created')).toMatch(/\d{1,2}\.\d{1,2}\.\d{4}/);
		expect(component.formatValue('desert', 'terrain')).toBe('Desert');
		expect(component.formatValue('', 'name')).toBe('');
	});

	it('toggleSort should toggle sort direction correctly', () => {
		component.sortField = 'name';
		component.sortDirection = 'asc';
		expect(component.toggleSort('name')).toBe('desc');
		component.sortDirection = 'desc';
		expect(component.toggleSort('name')).toBe('');
		component.sortDirection = '';
		expect(component.toggleSort('name')).toBe('asc');
		expect(component.toggleSort('other')).toBe('asc');
	});

	it('handlePageInput should call onPageChange with correct page', () => {
		spyOn(component, 'onPageChange');
		component.totalPages = 5;
		const event = { target: { value: '3' } } as any as Event;
		component.handlePageInput(event);
		expect(component.onPageChange).toHaveBeenCalledWith(2);
	});

	it('handlePageInput should not call onPageChange if input is NaN', () => {
		spyOn(component, 'onPageChange');
		const event = { target: { value: 'abc' } } as any as Event;
		component.handlePageInput(event);
		expect(component.onPageChange).not.toHaveBeenCalled();
	});

	it('goBack should call location.back', () => {
		component.goBack();
		expect(locationSpy.back).toHaveBeenCalled();
	});
});
