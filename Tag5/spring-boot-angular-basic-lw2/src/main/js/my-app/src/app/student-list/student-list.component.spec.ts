import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StudentListComponent } from './student-list.component';
import { StudentService } from '../service/student.service';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser';

// Fake-Daten für Test
const mockStudents = [
  { id: 1, name: 'Max Mustermann', email: 'max@test.de' },
  { id: 2, name: 'Erika Musterfrau', email: 'erika@test.de' }
];

// Mock-Service mit richtiger Methode
class MockStudentService {
  findAll() {
    return of(mockStudents);
  }
}

describe('StudentListComponent', () => {
  let component: StudentListComponent;
  let fixture: ComponentFixture<StudentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StudentListComponent],
      providers: [{ provide: StudentService, useClass: MockStudentService }]
    }).compileComponents();

    fixture = TestBed.createComponent(StudentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render student list in table', () => {
    const rows = fixture.debugElement.queryAll(By.css('tbody tr'));
    expect(rows.length).toBe(2);
    expect(rows[0].nativeElement.textContent).toContain('Max Mustermann');
    expect(rows[1].nativeElement.textContent).toContain('Erika Musterfrau');
  });
});
