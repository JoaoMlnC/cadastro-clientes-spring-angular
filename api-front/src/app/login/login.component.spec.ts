import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../service/auth.service'; // Ajuste o caminho conforme necessário
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [FormsModule, RouterModule] // Adicione o RouterModule aqui
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.router.navigate(['/clientes']);
      },
      error: (err) => {
        this.errorMessage = 'Usuário ou senha inválidos!';
      },
    });
  }
  onLogout() {
    this.authService.logout(); 
    this.router.navigate(['/login']); 
  }
}
