import { Posicion } from "./posicion";
export interface Futbolista {
  id: number;
  nombres: string;
  apellidos: string;
  fechaDeNacimiento: Date;
  caracteristicas: string;
  posicion: Posicion;
}
