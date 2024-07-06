export interface Posicion {
  id: number;
  nombre: string;
  descripcion: string;
}

// Ejemplo de definición de Posicion si es una clase
export class Posicion {
  constructor(public id: number, public nombre: string, public descripcion: string) {}
}
