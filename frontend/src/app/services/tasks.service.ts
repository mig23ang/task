import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TasksService {
  private readonly baseUrl = environment.apiBaseUrl + '/tasks';

  constructor(private http: HttpClient) {}

  // Crear una tarea
  createTask(task: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, task);
  }

  // Actualizar una tarea por ID
  updateTask(taskId: number, task: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${taskId}`, task);
  }

  // Obtener todas las tareas
  getAllTasks(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }
  // Obtener una tarea por id
  getTask(taskId: any): Observable<any> {
    return this.http.get(`${this.baseUrl}/${taskId}`);
  }

  // Obtener tareas por estado
  getTasksByStatus(status: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/status/${status}`);
  }

  // Obtener tareas por usuario
  getTasksByUser(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${userId}`);
  }
}
