import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { StudentFormComponent } from './student-form.component';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../service/student.service';

// Mock-Service für StudentService
class MockStudentService {
  save() {
    return {}; // Dummy-Implementierung, wird im Test nicht wirklich genutzt
  }
}

describe('StudentFormComponent', () => {
  let component: StudentFormComponent;
  let fixture: ComponentFixture<StudentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StudentFormComponent],
      imports: [FormsModule], // nötig für [(ngModel)]
      providers: [
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: new Map() } } },
        { provide: StudentService, useClass: MockStudentService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(StudentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render form with name and email input', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('input[name="name"]')).toBeTruthy();
    expect(compiled.querySelector('input[name="email"]')).toBeTruthy();
  });
});
