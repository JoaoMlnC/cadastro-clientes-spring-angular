import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', 
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/login'; 
  private isLoggedIn: boolean = false; 

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(this.apiUrl, { username, password });
  }

  
  isAuthenticated(): boolean {
    return this.isLoggedIn; 
  }

  
  setAuthenticated(isAuthenticated: boolean): void {
    this.isLoggedIn = isAuthenticated;
  }

  logout(): void {
    this.setAuthenticated(false); 
    localStorage.removeItem('token'); 
  }
}

