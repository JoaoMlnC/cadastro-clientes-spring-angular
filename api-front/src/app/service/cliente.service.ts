import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscriber } from 'rxjs';
import { Cliente } from '../model/Cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  //Url da API
  private url:string="http://localhost:8080/clientes";

  constructor(private http:HttpClient) { }

  //Metodo para selecionar todos os clientes

  listarClientes():Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.url + "/listarClientes");
  }

  cadastrarClientes(obj:Cliente):Observable<Cliente> {
    return this.http.post<Cliente>(this.url + "/cadastrar", obj)
  }

  editarClientes(obj:Cliente):Observable<Cliente> {
    return this.http.put<Cliente>(this.url + "/editar", obj)
  }

  removerClientes(codigo:number):Observable<void>{
    return this.http.delete<void>(this.url + "/remover/" + codigo )
  }
}
