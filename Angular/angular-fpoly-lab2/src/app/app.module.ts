import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ProducListComponent } from './produc-list/produc-list.component';
import {FormsModule} from "@angular/forms";
import {StarComponent} from "./start/star.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProducListComponent,
    StarComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
