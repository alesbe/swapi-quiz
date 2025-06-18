import { Component, OnInit, inject } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule, Location } from '@angular/common';
import { debounceTime } from 'rxjs/operators';
import { PeopleService, PeopleResponse } from '../../services/people.service';
import { Person } from '../shared/models/person.model';

@Component({
	selector: 'app-people',
	standalone: true,
	imports: [CommonModule, ReactiveFormsModule],
	templateUrl: './people.component.html',
	styleUrls: ['./people.component.scss']
})
export class PeopleComponent implements OnInit {
	private readonly peopleService = inject(PeopleService);
  private location = inject(Location);

	searchControl = new FormControl('');
	people: Person[] = [];

	columns = [
    { label: 'Name', field: 'name', sortable: true },
    { label: 'Height', field: 'height' },
    { label: 'Mass', field: 'mass' },
    { label: 'Hair Color', field: 'hairColor' },
    { label: 'Skin Color', field: 'skinColor' },
    { label: 'Eye Color', field: 'eyeColor' },
    { label: 'Birth Year', field: 'birthYear' },
    { label: 'Gender', field: 'gender' },
    { label: 'Homeworld', field: 'homeworldName' },
    { label: 'Created', field: 'created', sortable: true },
    { label: 'Edited', field: 'edited' }
  ];

	loading = false;
	currentPage = 0;
	totalPages = 1;
	sortField: string = 'name';
	sortDirection: 'asc' | 'desc' | '' = 'asc';

	ngOnInit() {
		this.loadPeople();

		this.searchControl.valueChanges
			.pipe(debounceTime(500))
			.subscribe(() => {
				this.currentPage = 0;
				this.loadPeople();
			});
	}

	loadPeople() {
    this.loading = true;

    this.peopleService
      .getPeople({
        page: this.currentPage,
        size: 15,
        search: this.searchControl.value || '',
        sort: this.sortField,
        direction: this.sortDirection || 'asc'
      })
      .subscribe({
        next: (response: PeopleResponse) => {
          this.people = response.content.map(p => ({
            ...p,
            homeworldName: p.homeworld && p.homeworld.name?.toLowerCase() !== 'unknown'
              ? p.homeworld.name
              : null
          }));
          this.totalPages = response.totalPages;
          this.loading = false;
        },
        error: () => {
          this.people = [];
          this.totalPages = 1;
          this.loading = false;
        }
      });
  }

	onSort(event: { field: string; direction: 'asc' | 'desc' | '' }) {
		this.sortField = event.field;
		this.sortDirection = event.direction || 'asc';
		this.currentPage = 0;
		this.loadPeople();
	}

	onPageChange(newPage: number) {
		if (newPage >= 0 && newPage < this.totalPages) {
			this.currentPage = newPage;
			this.loadPeople();
		}
	}

	getValue(item: Person, field: string): any {
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

    if (field === 'gender' && typeof value === 'string') {
      return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
    }

    if (field === 'homeworldName') {
      return value || '*Unknown*';
    }

    // Capitalize only the first letter of the entire string
    if (
      ['hairColor', 'skinColor', 'eyeColor'].includes(field) &&
      typeof value === 'string' &&
      value.length > 0
    ) {
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

    page = Math.max(1, Math.min(this.totalPages, page)); // Limits between 1 and totalPages

    this.onPageChange(page - 1); // Convert to zero-based index
  }

  goBack() {
		this.location.back();
	}
}
