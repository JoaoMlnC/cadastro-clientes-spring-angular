

export class Cliente {
    id: number; 
    username: string;
    password: string;
    cnpj: string;
    razaoSocial: string;
    status: string;

    constructor() {
        this.id = 0;
        this.username = '';
        this.password = '';
        this.cnpj = '';
        this.razaoSocial = '';
        this.status = '';
    }
}
