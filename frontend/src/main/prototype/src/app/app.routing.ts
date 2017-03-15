import {Routes, RouterModule} from "@angular/router";
import {PessoaListComponent} from "./pessoa/pessoa-list.component";
import {ModuleWithProviders} from "@angular/core";
import {PessoaNewComponent} from "./pessoa/pessoa-new.component";
import {PessoaEditComponent} from "./pessoa/pessoa-edit.component";


const appRoutes: Routes = [
  {path: '', redirectTo: 'pessoas/list', pathMatch: 'full'},
  {path: 'pessoas/list', component: PessoaListComponent},
  {path: 'pessoas/new', component: PessoaNewComponent},
  {path: 'pessoas/:id/edit', component: PessoaEditComponent}

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
