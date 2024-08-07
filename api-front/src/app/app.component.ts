import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; // Importando RouterOutlet para gerenciamento de rotas

@Component({
  selector: 'app-root',
  template: `
    <router-outlet></router-outlet>
  `,
  standalone: true,
  imports: [RouterOutlet] // Importando o RouterOutlet aqui
})
export class AppComponent {
  title = 'api-front';
}
