import {Pessoa} from "./pessoa";
import {PessoaService} from "./pessoa.service";
import {Router} from "@angular/router";
import {Component} from "@angular/core";

@Component({
  selector: 'pessoa-new',
  templateUrl: 'pessoa-new.component.html',
  moduleId: module.id
})
export class PessoaNewComponent {
  pessoa: Pessoa = {
    id: "",
    email: "",
    nome: "",
    telefone: "",
    endereco: ""
  };

  constructor(private service: PessoaService, private router: Router) {
  }

  submit() {
    this.service.create(this.pessoa);
    this.router.navigate(['pessoas', 'list']);
  }
}
