import { DeveloperPortalPage } from './app.po';

describe('developer-portal App', () => {
  let page: DeveloperPortalPage;

  beforeEach(() => {
    page = new DeveloperPortalPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
