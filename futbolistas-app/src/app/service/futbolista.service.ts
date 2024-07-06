import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Futbolista } from '../model/futbolista';

@Injectable({
  providedIn: "root"
})
export class FutbolistaService {
  private readonly API_URL = `${environment.urlHost}futbolista`;

  constructor(private http: HttpClient) { }

  getFutbolistas(page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(`${this.API_URL}`, { params }).pipe(
      catchError(this.handleError)
    );
  }
  getFutbolistaById(id: number): Observable<Futbolista> {
    return this.http.get<Futbolista>(`${this.API_URL}/${id}`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('Se ha producido un error:', error);
    return throwError(() => new Error('Algo falló. Por favor, intente nuevamente.'));
  }
}
