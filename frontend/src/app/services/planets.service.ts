import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Planet } from '../components/shared/models/planet.model';
import { environment } from '../../../environments/environment';

export interface PlanetResponse {
	content: Planet[];
	totalPages: number;
	totalElements: number;
	pageable: {
		pageNumber: number;
		pageSize: number;
	};
}

export interface PlanetQueryParams {
	page: number;
	size: number;
	search?: string;
	sort?: string;
	direction?: 'asc' | 'desc';
}

@Injectable({
	providedIn: 'root'
})
export class PlanetsService {
	private readonly baseUrl = `${environment.apiUrl}/planets`;

	constructor(private http: HttpClient) {}

	getPlanets(params: PlanetQueryParams): Observable<PlanetResponse> {
		const query = new URLSearchParams();

		query.set('page', params.page.toString());
		query.set('size', params.size.toString());

		if (params.search) query.set('search', params.search);
		if (params.sort) query.set('sort', params.sort);
		if (params.direction) query.set('direction', params.direction);

		return this.http.get<PlanetResponse>(`${this.baseUrl}?${query.toString()}`);
	}
}
