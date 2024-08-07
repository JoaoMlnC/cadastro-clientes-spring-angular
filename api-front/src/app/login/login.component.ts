// login.component.ts
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Importe o FormsModule aqui se for necessário
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true, // Adicione esta propriedade para torná-lo um componente standalone
  imports: [FormsModule, CommonModule], // Adicione o FormsModule aqui se for necessário
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        console.log('Login bem-sucedido!'); // Verifique se isso é exibido
        this.authService.setAuthenticated(true);
        this.router.navigate(['/clientes']); // Redirecionar após o login bem-sucedido
      },
      error: (err) => {
        console.error('Erro no login:', err); // Mostre o erro no console
        this.errorMessage = 'Usuário ou senha inválidos!';
      },
    });
  }
}
