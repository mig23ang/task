import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsersService } from '../../services/users.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-created-users',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './created-users.component.html',
  styleUrls: ['./created-users.component.scss']
})
export class CreatedUsersComponent {
  userForm: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private usersService: UsersService) {
    this.userForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      role: ['', [Validators.required]],
    });
  }

  createUser() {
    if (this.userForm.invalid) {
      return; // No enviar si el formulario es inválido
    }

    const user = this.userForm.value;

    this.usersService.createUser(user).subscribe({
      next: () => {
        this.successMessage = 'Usuario creado con éxito!';
        this.userForm.reset();
      },
      error: () => {
        this.errorMessage = 'Hubo un error al crear el usuario. Inténtalo de nuevo.';
      }
    });
  }
}
