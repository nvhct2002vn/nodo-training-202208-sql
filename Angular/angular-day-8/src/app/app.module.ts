import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthorsListComponent} from './authors/authors-list.component';
import {AuthorsDetailsComponent} from "./authors/authors-details.component";

@NgModule({
  declarations: [
    AppComponent,
    AuthorsListComponent,
    AuthorsDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
