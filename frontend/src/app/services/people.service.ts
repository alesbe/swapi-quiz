import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Person } from '../components/shared/models/person.model';
import { environment } from '../../../environments/environment';

export interface PeopleResponse {
	content: Person[];
	totalPages: number;
	totalElements: number;
	pageable: {
		pageNumber: number;
		pageSize: number;
	};
}

export interface PeopleQueryParams {
	page: number;
	size: number;
	search?: string;
	sort?: string;
	direction?: 'asc' | 'desc';
}

@Injectable({
	providedIn: 'root'
})
export class PeopleService {
	private readonly baseUrl = `${environment.apiUrl}/people`;

	constructor(private http: HttpClient) {}

	getPeople(params: PeopleQueryParams): Observable<PeopleResponse> {
		const query = new URLSearchParams();

		query.set('page', params.page.toString());
		query.set('size', params.size.toString());

		if (params.search) query.set('search', params.search);
		if (params.sort) query.set('sort', params.sort);
		if (params.direction) query.set('direction', params.direction);

		return this.http.get<PeopleResponse>(`${this.baseUrl}?${query.toString()}`);
	}
}
