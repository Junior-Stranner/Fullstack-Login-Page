import { Component, signal} from '@angular/core';
import { FormControl, FormGroup,ReactiveFormsModule ,Validators } from '@angular/forms';
import { BtnPrimaryComponent } from "../btn-primary/btn-primary.component";

@Component({
    selector: 'app-newsletter-from',
    standalone: true,
    templateUrl: './newsletter-from.component.html',
    styleUrl: './newsletter-from.component.scss',
    imports: [BtnPrimaryComponent,ReactiveFormsModule],
})
export class NewsletterFromComponent {
  newsletterForm!: FormGroup;
  loading = signal(false);

  constructor(){
    this.newsletterForm = new FormGroup({
      name: new FormControl('',[Validators.required]),
      email: new FormControl('',[Validators.required, Validators.email])
    })
  }
  onSubmit(){
   console.log(this.newsletterForm.value)
  }
}
