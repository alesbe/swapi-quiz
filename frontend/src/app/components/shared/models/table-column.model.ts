export interface TableColumn {
	label: string; // Visible text
	field: string; // Object key in data
	sortable?: boolean;
}