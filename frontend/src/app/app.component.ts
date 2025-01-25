import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { CreatedUsersComponent } from './components/created-users/created-users.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'front';
}
