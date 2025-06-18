import { Component, OnInit, inject } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule, Location } from '@angular/common';
import { debounceTime } from 'rxjs/operators';
import { Planet } from '../shared/models/planet.model';
import { PlanetResponse, PlanetsService } from '../../services/planets.service';

@Component({
  selector: 'app-planets',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './planets.component.html',
  styleUrls: ['./planets.component.scss']
})
export class PlanetsComponent implements OnInit {
  private readonly planetService = inject(PlanetsService);
  private location = inject(Location);

  searchControl = new FormControl('');
  planets: Planet[] = [];

  columns = [
    { label: 'Name', field: 'name', sortable: true },
    { label: 'Rotation Period', field: 'rotationPeriod' },
    { label: 'Orbital Period', field: 'orbitalPeriod' },
    { label: 'Diameter', field: 'diameter' },
    { label: 'Climate', field: 'climate' },
    { label: 'Gravity', field: 'gravity' },
    { label: 'Terrain', field: 'terrain' },
    { label: 'Surface Water', field: 'surfaceWater' },
    { label: 'Population', field: 'population' },
    { label: 'Created', field: 'created', sortable: true },
    { label: 'Edited', field: 'edited' }
  ];

  loading = false;
  currentPage = 0;
  totalPages = 1;
  sortField: string = 'name';
  sortDirection: 'asc' | 'desc' | '' = 'asc';

  ngOnInit() {
    this.loadPlanets();

    this.searchControl.valueChanges
      .pipe(debounceTime(500))
      .subscribe(() => {
        this.currentPage = 0;
        this.loadPlanets();
      });
  }

  loadPlanets() {
    this.loading = true;

    this.planetService
      .getPlanets({
        page: this.currentPage,
        size: 15,
        search: this.searchControl.value || '',
        sort: this.sortField,
        direction: this.sortDirection || 'asc'
      })
      .subscribe({
        next: (response: PlanetResponse) => {
          this.planets = response.content;
          this.totalPages = response.totalPages;
          this.loading = false;
        },
        error: () => {
          this.planets = [];
          this.totalPages = 1;
          this.loading = false;
        }
      });
  }

  onSort(event: { field: string; direction: 'asc' | 'desc' | '' }) {
    this.sortField = event.field;
    this.sortDirection = event.direction || 'asc';
    this.currentPage = 0;
    this.loadPlanets();
  }

  onPageChange(newPage: number) {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.currentPage = newPage;
      this.loadPlanets();
    }
  }

  getValue(item: Planet, field: string): any {
    return (item as any)[field];
  }

  sanitizeValue(value: any): string {
    if (value === null || value?.toString().toLowerCase() === 'unknown') return 'Unknown';
    if (value?.toString().toLowerCase() === 'n/a') return '-';
    return '';
  }

  formatValue(value: any, field: string): string {
    if (value === null || value === 'unknown') return '*Unknown*';
    if (value === 'n/a') return '-';

    if (field === 'created' || field === 'edited') {
      const date = new Date(value);
      return isNaN(date.getTime()) ? '*Unknown*' : date.toLocaleDateString('de-DE');
    }

    if (typeof value === 'string' && value.length > 0) {
      return value.charAt(0).toUpperCase() + value.slice(1);
    }

    return String(value);
  }

  toggleSort(field: string): 'asc' | 'desc' | '' {
    if (this.sortField !== field) return 'asc';
    if (this.sortDirection === 'asc') return 'desc';
    if (this.sortDirection === 'desc') return '';
    return 'asc';
  }

  handlePageInput(event: Event) {
    const input = event.target as HTMLInputElement;
    let page = Number(input.value);

    if (isNaN(page)) return;
    page = Math.max(1, Math.min(this.totalPages, page));
    this.onPageChange(page - 1);
  }

  goBack() {
    this.location.back();
  }
}
