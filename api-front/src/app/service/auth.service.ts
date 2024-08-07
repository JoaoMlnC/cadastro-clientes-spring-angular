import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // O serviço será injetado em toda a aplicação
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/login'; // Ajuste a URL conforme necessário
  private isLoggedIn: boolean = false; // Adicionando uma propriedade para controlar o estado de autenticação

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(this.apiUrl, { username, password });
  }

  // Método para verificar se o usuário está autenticado
  isAuthenticated(): boolean {
    return this.isLoggedIn; // Retorna true se o usuário estiver autenticado
  }

  // Método para definir o estado de login
  setAuthenticated(isAuthenticated: boolean): void {
    this.isLoggedIn = isAuthenticated;
  }
}

