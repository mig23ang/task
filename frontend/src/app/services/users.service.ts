import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private readonly baseUrl = environment.apiBaseUrl + '/users';

  constructor(private http: HttpClient) {}

  // Crear un usuario
  createUser(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  // Obtener todos los usuarios
  getAllUsers(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  // Obtener usuarios filtrados por rol (extensión del endpoint si es necesario)
  getUsersByRole(role: string): Observable<any> {
    return this.http.get(`${this.baseUrl}?role=${role}`);
  }
}
