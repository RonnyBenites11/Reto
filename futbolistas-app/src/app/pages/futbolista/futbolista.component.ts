import { Component, OnInit } from '@angular/core';
import { Futbolista } from 'src/app/model/futbolista';
import { FutbolistaService } from 'src/app/service/futbolista.service';

@Component({
  selector: 'app-futbolista',
  templateUrl: './futbolista.component.html',
  styleUrls: ['./futbolista.component.css']
})
export class FutbolistaComponent implements OnInit {
  futbolistas: Futbolista[] = [];
  page: number = 1; // Empieza en 1 para la paginación
  size: number = 10;
  totalElements: number = 0;
  futbolista: Futbolista | null = null;
  futbolistaId: number = 1;  // ID del futbolista que deseas consultar

  constructor(private futbolistaService: FutbolistaService) { }

  ngOnInit(): void {
    this.loadFutbolistas();
  }

  loadFutbolistas(): void {
    // Aseguramos que page y size sean números enteros válidos y positivos
    if (!Number.isInteger(this.page) || this.page < 1) {
      this.page = 1; // Si no es válido, restablecemos a 1
    }
    if (!Number.isInteger(this.size) || this.size < 1) {
      this.size = 10; // Si no es válido, restablecemos a 10
    }

    this.futbolistaService.getFutbolistas(this.page - 1, this.size).subscribe({
      next: (response) => {
        this.futbolistas = response.content;
        this.totalElements = response.totalElements;
      },
      error: (error) => {
        console.error('Error al cargar futbolistas:', error);
      }
    });
  }

  onPageChange(page: number): void {
    this.page = page;
    this.loadFutbolistas();
  }

  cargarFutbolista(): void {
    this.futbolistaService.getFutbolistaById(this.futbolistaId).subscribe({
      next: (response) => {
        this.futbolista = response;
        alert(`ID: ${this.futbolista.id}\nNombre: ${this.futbolista.nombres}\nPosición: ${this.futbolista.posicion}`);
      },
      error: (error) => {
        console.error('Error al cargar futbolista:', error);
      }
    });
  }
}
