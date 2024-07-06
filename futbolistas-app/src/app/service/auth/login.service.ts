import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import  {  Observable, throwError, BehaviorSubject } from 'rxjs';
import { tap, catchError,map } from 'rxjs/operators';
import { User } from './user';
import { environment } from 'src/environments/environment';
import { AuthService } from 'src/app/guards/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private readonly API_URL = `${environment.urlHost}auth/login`;

  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("");

  constructor(private http: HttpClient, private authService: AuthService) {
    const token = this.authService.getToken();
    this.currentUserLoginOn.next(token != null);
    this.currentUserData.next(token || "");
  }

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post<any>(this.API_URL, credentials).pipe(
      tap((response) => {
        this.authService.setToken(response.token);
        this.currentUserData.next(response.token);
        this.currentUserLoginOn.next(true);
      }),
      map((response) => response.token),
      catchError(this.handleError)
    );
  }

  logout(): void {
    this.authService.logout();
    this.currentUserLoginOn.next(false);
    this.currentUserData.next("");
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se ha producido un error ', error.error);
    } else {
      console.error('Backend retornó el código de estado ', error.status, error.error);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }

  get userData(): Observable<String> {
    return this.currentUserData.asObservable();
  }

  get userLoginOn(): Observable<boolean> {
    return this.currentUserLoginOn.asObservable();
  }

  get userToken(): String {
    return this.currentUserData.value;
  }
}
