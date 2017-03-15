import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {PessoaService} from "./pessoa/pessoa.service";
import {PessoaListComponent} from "./pessoa/pessoa-list.component";
import {routing} from "./app.routing";
import {PessoaNewComponent} from "./pessoa/pessoa-new.component";
import {PessoaEditComponent} from "./pessoa/pessoa-edit.component";
@NgModule({
  declarations: [
    AppComponent, PessoaListComponent, PessoaNewComponent, PessoaEditComponent
  ],
  imports: [
    BrowserModule, FormsModule, HttpModule, routing
  ],

  providers: [PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
