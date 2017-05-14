import { WhobrokeitlastPage } from './app.po';

describe('whobrokeitlast App', () => {
  let page: WhobrokeitlastPage;

  beforeEach(() => {
    page = new WhobrokeitlastPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
