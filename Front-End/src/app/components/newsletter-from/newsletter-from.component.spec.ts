import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterFromComponent } from './newsletter-from.component';

describe('NewsletterFromComponent', () => {
  let component: NewsletterFromComponent;
  let fixture: ComponentFixture<NewsletterFromComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewsletterFromComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewsletterFromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
