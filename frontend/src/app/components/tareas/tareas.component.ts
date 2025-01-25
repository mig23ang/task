import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-tareas',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  templateUrl: './tareas.component.html',
  styleUrl: './tareas.component.scss',
  providers: [TasksService],
})
export class TareasComponent {
  tasks: any[] = [];
  errorMessage: string | null = null;

  // Filtros
  userId: number | null = null;
  status: string | null = null;

  constructor(private tasksService: TasksService, private router: Router) {}

  // Cargar tareas por usuario
  loadTasksByUser(): void {
    if (this.userId === null) {
      this.errorMessage = 'Por favor, ingresa un ID de usuario.';
      return;
    }
    this.tasksService.getTasksByUser(this.userId).subscribe({
      next: (response) => {
        this.tasks = response;
        this.errorMessage = null;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar las tareas del usuario.';
        console.error(error);
      },
    });
  }

  // Cargar tareas por estado
  loadTasksByStatus(): void {
    if (!this.status) {
      this.errorMessage = 'Por favor, selecciona un estado.';
      return;
    }
    this.tasksService.getTasksByStatus(this.status).subscribe({
      next: (response) => {
        this.tasks = response;
        this.errorMessage = null;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar las tareas por estado.';
        console.error(error);
      },
    });
  }

  // Navegar al formulario de edición con el ID de la tarea
  editTask(taskId: number): void {
    this.router.navigate(['/tasks/edit', taskId]);
  }

  // Limpiar filtros y tareas
  clearFilters(): void {
    this.userId = null;
    this.status = null;
    this.tasks = [];
    this.errorMessage = null;
  }
}
