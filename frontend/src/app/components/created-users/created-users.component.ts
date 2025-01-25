import { Component, Inject, Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsersService } from '../../services/users.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-created-users',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  templateUrl: './created-users.component.html',
  styleUrls: ['./created-users.component.scss'],
  providers: [UsersService],
})
export class CreatedUsersComponent {
  user = {
    name: '',
    email: '',
    role: 'DEVELOPER',
  };
  constructor(private userService: UsersService, private router: Router) {}

  onSubmit() {
    console.log('Formulario enviado', this.user);


    this.userService.createUser(this.user).subscribe({
      next: (response) => {
        console.log('Usuario creado exitosamente', response);
        //navegar a todos los usuarios
        this.router.navigate(['/users/all']);
      },
      error: (error) => {
        alert('Error al crear el usuario. Por favor, intente nuevamente.');
        this.resetForm();
      },
      complete: () => {
        console.log('Proceso de creación de usuario completado');
      },
    });
  }

  resetForm() {
    this.user = {
      name: '',
      email: '',
      role: 'DEVELOPER', // Restablecemos el valor por defecto
    };
  }
}
