import { Component } from '@angular/core';
import { TasksService } from '../../services/tasks.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-crear-tareas',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  templateUrl: './crear-tareas.component.html',
  styleUrl: './crear-tareas.component.scss',
  providers: [TasksService],
})
export class CrearTareasComponent {
  task = {
    description: '',
    status: 'PENDING',
    priority: 'MEDIUM',
    assignedTo: {
      id: null,
    },
  };

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private tasksService: TasksService, private router: Router) {}

  ngOnInit(): void {
    // Aquí puedes agregar lógica al inicializar el componente si es necesario
  }

  onSubmit(): void {
    console.log('Enviando tarea', this.task);

    this.tasksService.createTask(this.task).subscribe({
      next: (response) => {
        console.log('Tarea creada exitosamente:', response);
        this.successMessage = 'La tarea fue creada exitosamente.';
        this.router.navigate(['/tasks/all']);
      },
      error: (error) => {
        console.error('Error al crear la tarea:', error);
        this.errorMessage =
          'Ocurrió un error al crear la tarea. Intenta nuevamente.';
        this.clearForm();
      },
    });
  }

  clearForm(): void {
    this.task = {
      description: '',
      status: 'PENDING',
      priority: 'MEDIUM',
      assignedTo: {
        id: null,
      },
    };
  }
}
