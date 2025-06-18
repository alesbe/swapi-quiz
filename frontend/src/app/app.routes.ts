import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PeopleComponent } from './components/people/people.component';
import { PlanetsComponent } from './components/planets/planets.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'people', component: PeopleComponent },
  { path: 'planets', component: PlanetsComponent },
  { path: '**', redirectTo: '' }
];
