/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author MSI
 */

public class SiteTest {

    public static void main(String[] args) {
        String name = ("home");
        Site site = new Site(name);
        Integer option;
        do {
            System.out.println("0: quit");
            System.out.println("1: add page to current page");
            System.out.println("2: find page");
            System.out.println("3: move down");
            System.out.println("4: move up");
            System.out.println("5: display current page");
            System.out.println("6: display site map");
            option = Input.getInteger("input option: ");
            switch (option) {
                case 1:
                    name = Input.getString("input the page name: ");
                    site.addSubpage(name);
                    break;
                case 2:
                    name = Input.getString("input the home page name: ");
                    try{
                        site.findpage(name);
                        System.out.println(site.getCurrentPage());
                    } catch(Site.PageNotFoundException e){
                        System.out.println("not found");
                    } 
                    break;
                case 3:
                    name = Input.getString("enter the name of the page to move to : ");
                    site.moveDown(name);
                    break;
                case 4:
                    site.moveUp();
                    break;
                case 5:
                    System.out.println(site.getCurrentPage());
                    break;
                case 6:
                    System.out.println(site);
                    break;
            }
        } while (option != 0);
    }

}
