import {Pessoa} from "./pessoa";
import {Injectable} from "@angular/core";

let PESSOAS: Pessoa[] = [
  {id: "1", nome: "Nome01", email: "nome01@email.com.br", telefone: "454599", endereco: ""},
  {id: "2", nome: "Nome02", email: "nome02@email.com.br", telefone: "454599", endereco: ""},
  {id: "3", nome: "Nome03", email: "nome03@email.com.br", telefone: "454599", endereco: ""},
  {id: "4", nome: "Nome04", email: "nome04@email.com.br", telefone: "454599", endereco: ""},
  {id: "5", nome: "Nome05", email: "nome05@email.com.br", telefone: "454599", endereco: ""},
  {id: "6", nome: "Nome06", email: "nome06@email.com.br", telefone: "454599", endereco: ""},
  {id: "7", nome: "Nome07", email: "nome07@email.com.br", telefone: "454599", endereco: ""},
  {id: "8", nome: "Nome08", email: "nome08@email.com.br", telefone: "454599", endereco: ""},
  {id: "9", nome: "Nome09", email: "nome09@email.com.br", telefone: "454599", endereco: ""},
  {id: "10", nome: "Nome10", email: "nome10@email.com.br", telefone: "454599", endereco: ""}
];
@Injectable()
export class PessoaService {

  getPessoas(): Pessoa[] {
    return PESSOAS;
  }

  getPessoa(id: string): Pessoa|null {
    let array = this.getPessoas().filter((pessoa: Pessoa) => pessoa.id == id);
    return array.length ? array[0] : null;
  }

  create(pessoa: Pessoa): void {
    pessoa.id = String(this.getPessoas().length + 1);
    this.getPessoas().push(pessoa);
  }

  delete(pessoa: Pessoa) {
    let index = this.getPessoas().findIndex(item => item.id == pessoa.id);
    if (index != -1) {
      this.getPessoas().splice(index, 1);
    }
  }
}
