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
  styleUrl: './principal.component.css'
})
export class PrincipalComponent {

  //Método de inicialização
  ngOnInit(){
    this.listarClientes();
  }

  // Objeto do tipo Cliente
  cliente = new Cliente();

  //Variável para visibilidade dos botões
  btnCadastro:boolean = true;

  //Variável para visibilidade da tabela
  tabela:boolean = true;

  //Json de Clientes
  clientes:Cliente[] = [];

  //Construtor
  constructor(private service:ClienteService){}
  
  //Método de seleção
  listarClientes():void {
    this.service.listarClientes().subscribe(retorno => this.clientes = retorno);
  }

  //Método de cadastro
  cadastrarClientes():void{
    this.service.cadastrarClientes(this.cliente)
    .subscribe(retorno => {
      
      //cadastrar o cliente no vetor
      this.clientes.push(retorno)
      
      //limpar formulário
      this.cliente= new Cliente();

      //Mensagem
      alert("Cliente cadastrado com Sucesso!")
    });
  }

  //Metodo para selecionar um cliente específico
  selecionarCliente(posicao:number):void{
    
    //selecionar o cliente no vetor
    this.cliente = this.clientes[posicao];

    //visibilidade dos botões
    this.btnCadastro = false;

    //visibilidade da tabela
    this.tabela = false;
  }

  //Método para editar clientes
  editarCliente():void{
    this.service.editarClientes(this.cliente)
    .subscribe(retorno => {
      
      //obter posição do vetor
      let posicao = this.clientes.findIndex(obj => {
        return obj.id == retorno.id;
      });

      //Alterar os dados dolciente do Vetor
      this.clientes[posicao] = retorno;

      //limpar formulário
      this.cliente= new Cliente();

      //Exibir o botão
      this.btnCadastro = true;

      //Visibilidade da Tabela
      this.tabela = true;

      alert("Cliente alterado com Sucesso!");
    });
  }

  //Método para editar clientes
  removerCliente():void{
    this.service.removerClientes(this.cliente.id)
    .subscribe(() => {
      
      //obter posição do vetor
      let posicao = this.clientes.findIndex(obj => {
        return obj.id == this.cliente.id;
      });

      //Remover cliente do vetor
      this.clientes.splice(posicao, 1);

      //limpar formulário
      this.cliente= new Cliente();

      //Exibir o botão
      this.btnCadastro = true;

      //Visibilidade da Tabela
      this.tabela = true;

      alert("Cliente Removido com Sucesso!");
    });
  }

  //Método para cancelar
  cancelarAcao():void{
     //limpar formulário
     this.cliente= new Cliente();

     //Exibir o botão
     this.btnCadastro = true;

     //Visibilidade da Tabela
     this.tabela = true;
  }

}
