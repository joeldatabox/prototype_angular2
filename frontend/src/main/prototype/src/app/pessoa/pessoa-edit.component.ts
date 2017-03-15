import {Component, OnInit} from "@angular/core";
import {PessoaService} from "./pessoa.service";
import {Router, ActivatedRoute, Params} from "@angular/router";
import {Pessoa} from "./pessoa";

@Component({
  selector: 'pessoa-edit',
  templateUrl: 'pessoa-edit.component.html',
  moduleId: module.id
})
export class PessoaEditComponent implements OnInit {
  pessoa: Pessoa;

  constructor(private service: PessoaService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = params['id'];
      this.pessoa = this.service.getPessoa(id);
      if (!this.pessoa) {
        alert('Registro não encontrado!');
      }
    });
  }

  submit(): void {
    /*this.messagesService.messages.push({
     type: 'success',
     message: 'Tarefa alterada com sucesso'
     });*/
    this.router.navigate(['pessoas', 'list']);
  }

}
