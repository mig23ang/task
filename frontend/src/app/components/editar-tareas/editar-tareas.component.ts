import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TasksService } from '../../services/tasks.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-editar-tareas',
  standalone: true,
  imports: [FormsModule, HttpClientModule, RouterModule, CommonModule],
  templateUrl: './editar-tareas.component.html',
  styleUrl: './editar-tareas.component.scss',
  providers: [TasksService],
})
export class EditarTareasComponent {
  task: any = {
    description: '',
    status: 'PENDING',
    priority: 'MEDIUM',
    assignedTo: { id: null },
  };
  errorMessage: string | null = null;
  taskId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private tasksService: TasksService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Obtener el ID de la tarea desde la URL
    this.taskId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.taskId) {
      this.loadTask();
    } else {
      this.errorMessage = 'ID de tarea no válido.';
    }
  }

  // Cargar datos de la tarea
  loadTask(): void {
    this.tasksService.getTask(this.taskId!).subscribe({
      next: (response) => {
        this.task = response;
        this.errorMessage = null;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar la tarea.';
        console.error(error);
      },
    });
  }

  // Guardar cambios
  saveTask(): void {
    if (!this.taskId) {
      this.errorMessage = 'No se puede guardar la tarea sin un ID válido.';
      return;
    }

    this.tasksService.updateTask(this.taskId, this.task).subscribe({
      next: () => {
        alert('Tarea actualizada con éxito.');
        this.router.navigate(['/tasks']);
      },
      error: (error) => {
        this.errorMessage = 'Error al actualizar la tarea.';
        console.error(error);
      },
    });
  }

  resetForm(): void {
    this.loadTask();
  }
}
