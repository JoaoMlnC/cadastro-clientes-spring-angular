import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cliente } from '../model/Cliente';
import { ClienteService } from '../service/cliente.service';

@Component({
  selector: 'app-principal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css'] // Corrigido para styleUrls
})
export class PrincipalComponent {
  // Propriedades para filtros
  filtroRazaoSocial: string = '';
  filtroCnpj: string = '';

  // Objeto do tipo Cliente
  cliente = new Cliente();
  clientes: Cliente[] = [];
  btnCadastro: boolean = true;
  tabela: boolean = true;

  public isSelecionar: boolean = false;

  constructor(private service: ClienteService) {}

  ngOnInit() {
    this.listarClientes();
  }

  listarClientes(): void {
    this.service.listarClientes().subscribe(retorno => this.clientes = retorno);
    console.log(this.clientes);
  }

  // Método de cadastro
  cadastrarClientes(): void {
    this.service.cadastrarClientes(this.cliente).subscribe(retorno => {
      this.clientes.push(retorno);
      this.cliente = new Cliente(); // Limpar formulário
      alert("Cliente cadastrado com Sucesso!");
      this.listarClientes(); // Atualizar a lista
    });
  }

  // Método de seleção
  selecionarCliente(posicao: number): void {
    this.isSelecionar = true;
    this.cliente = this.clientes[posicao];
    this.btnCadastro = false;
    this.tabela = false;
  }

  // Método para editar clientes
  editarCliente(): void {
    if (!this.cliente.username) {
      alert("Todos os campos são obrigatórios!");
      return;
    }
    this.service.editarClientes(this.cliente).subscribe(retorno => {
      let posicao = this.clientes.findIndex(obj => obj.id == retorno.id);
      this.clientes[posicao] = retorno;
      this.cliente = new Cliente();
      this.btnCadastro = true;
      this.tabela = true;
      this.listarClientes(); // Atualizar a lista
      alert("Cliente alterado com Sucesso!");
    });
  }

  // Método para remover clientes
  removerCliente(): void {
    this.service.removerClientes(this.cliente.id).subscribe(() => {
      let posicao = this.clientes.findIndex(obj => obj.id == this.cliente.id);
      this.clientes.splice(posicao, 1);
      this.cliente = new Cliente();
      this.btnCadastro = true;
      this.tabela = true;
      this.listarClientes(); // Atualizar a lista
      alert("Cliente Removido com Sucesso!");
    });
  }

  // Método para cancelar
  cancelarAcao(): void {
    this.cliente = new Cliente();
    this.btnCadastro = true;
    this.tabela = true;
  }

  // Método para buscar por razão social
  buscarPorRazaoSocial(): void {
    if (!this.filtroRazaoSocial) {
      alert('Por favor, insira uma razão social para buscar.');
      return;
    }
    
    this.service.buscarPorRazaoSocial(this.filtroRazaoSocial).subscribe(retorno => {
      this.clientes = retorno;
    });
  }

  // Método para buscar por CNPJ
  buscarPorCnpj(): void {
    if (!this.filtroCnpj) {
      alert('Por favor, insira um CNPJ para buscar.');
      return;
    }
    
    this.service.buscarPorCnpj(this.filtroCnpj).subscribe(retorno => {
      this.clientes = retorno;
    });
  }
}
