import { Component } from '@angular/core';
import { CreatedUsersComponent } from '../created-users/created-users.component';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [
    CreatedUsersComponent
  ],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.scss'
})
export class InicioComponent {

}
