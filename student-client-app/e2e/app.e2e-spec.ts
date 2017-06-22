import { StudentClientAppPage } from './app.po';

describe('student-client-app App', () => {
  let page: StudentClientAppPage;

  beforeEach(() => {
    page = new StudentClientAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
