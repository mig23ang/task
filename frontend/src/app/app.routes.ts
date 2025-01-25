import { Routes } from '@angular/router';
import { CreatedUsersComponent } from './components/created-users/created-users.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { UsersComponent } from './components/users/users.component';
import { CrearTareasComponent } from './components/crear-tareas/crear-tareas.component';
import { TareasComponent } from './components/tareas/tareas.component';
import { EditarTareasComponent } from './components/editar-tareas/editar-tareas.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: InicioComponent },
  { path: 'users/create', component: CreatedUsersComponent },
  { path: 'users/all', component: UsersComponent },
  { path: 'tasks/create', component: CrearTareasComponent },
  { path: 'tasks/all', component: TareasComponent },
  { path: 'tasks/edit/:id', component: EditarTareasComponent },
  { path: '**', redirectTo: 'home' },
];
