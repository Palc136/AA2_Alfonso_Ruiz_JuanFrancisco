import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.html',
  styleUrls: ['./contacts.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class ContactsComponent {
  contactForm = {
    name: '',
    email: '',
    message: ''
  };

  onSubmit() {
    console.log('Formulario enviado:', this.contactForm);
    alert('¡Mensaje enviado correctamente!');
    this.contactForm = { name: '', email: '', message: '' };
  }
}
