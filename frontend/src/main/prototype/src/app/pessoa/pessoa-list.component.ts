import {Component, OnInit} from "@angular/core";
import {PessoaService} from "./pessoa.service";
import {Pessoa} from "./pessoa";
import {Router} from "@angular/router";

@Component({
  selector: 'pessoa-list',
  templateUrl: 'pessoa-list.component.html',
  moduleId: module.id
})
export class PessoaListComponent implements OnInit {
  pessoas: Pessoa[];

  constructor(private service: PessoaService, private router: Router) {
  }

  goToEdit(pessoa: Pessoa) {
    this.router.navigate(['pessoas', pessoa.id, 'edit']);
  }

  delete(pessoa: Pessoa) {
    this.service.delete(pessoa);
  }

  ngOnInit(): void {
    this.pessoas = this.service.getPessoas();
  }
}
