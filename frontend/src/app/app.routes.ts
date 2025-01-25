import { Routes } from '@angular/router';
import { CreatedUsersComponent } from './components/created-users/created-users.component';
import { InicioComponent } from './components/inicio/inicio.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: InicioComponent },
  { path: 'users/create', component: CreatedUsersComponent },
  { path: '**', redirectTo: 'home' },
];
