import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
  providers: [UsersService],
})
export class UsersComponent implements OnInit {
  users: any[] = []; // Lista para almacenar los usuarios
  errorMessage: string | null = null;

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    this.fetchUsers(); // Llamamos al método para obtener usuarios al inicializar el componente
  }

  // Método para obtener los usuarios
  fetchUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (response) => {
        console.log('Usuarios obtenidos:', response);
        this.users = response;
      },
      error: (error) => {
        console.error('Error al obtener los usuarios:', error);
        this.errorMessage =
          'No se pudieron obtener los usuarios. Intenta de nuevo más tarde.';
      },
    });
  }
}
