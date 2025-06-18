import { Planet } from './planet.model';

export interface Person {
	name: string;
	height: number | null;
	mass: number | null;
	hairColor: string;
	skinColor: string;
	eyeColor: string;
	birthYear: string;
	gender: string;
	homeworld: Planet | null;
	created: string;
	edited: string;

	// Additional field to store homeworld name for display purposes
	homeworldName?: string | null;
}