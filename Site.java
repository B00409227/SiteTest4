/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */

public class Site {
    
    public class PageNotFoundException extends Exception{}

    private class PageNode {
        private String name;
        private PageNode above;
        private PageNode across;
        private PageNode below;
    }

    private PageNode homepage;
    private PageNode currentpage;

    public Site(String homepageName) {
        this.homepage = new PageNode();
        this.homepage.name = homepageName;
        this.currentpage = this.homepage;
    }

    public String toString() {
        String siteDetails = new String();
        siteDetails += this.homepage.name + "\n";
        PageNode page = this.homepage.below;
        if (page == null) {
            siteDetails += "  has no sub pages\n";
        } else {
            while (page != null) {
                siteDetails += "  " + page.name + "\n";
                siteDetails += this.getPage(page);
                page = page.across;
            }
        }
        return siteDetails;
    }

    private String getPage(PageNode page) {
        String subpagesDetails = new String();
        page = page.below;
        if (page == null) {
            subpagesDetails += "";
        } else {
            while (page != null) {
                subpagesDetails += "    " + page.name + "\n";
                subpagesDetails += this.getSubpages(page);
                page = page.across;
            }
        }
        return subpagesDetails;
    }
    
        private String getSubpages(PageNode page) {
        String subpagesDetails = new String();
        page = page.below;
        if (page == null) {
            subpagesDetails += "    has no sub pages\n";
        } else {
            while (page != null) {
                subpagesDetails += "      " + page.name + "\n";
                page = page.across;
            }
        }
        return subpagesDetails;
    }
        

    public void addSubpage(String name) {
        PageNode page = new PageNode();
        page.name = name;
        page.above = this.currentpage;
        if (this.currentpage.below == null) {
            this.currentpage.below = page;
        } else {
            PageNode next = this.currentpage.below;
            while (next.across != null) {
                next = next.across;
            }
            next.across = page;
        }
    }

    public void findpage(String name) throws PageNotFoundException{
        PageNode page;
        PageNode subpage;
        if (name.equalsIgnoreCase(this.homepage.name)) {
            page = this.homepage;
        } else {
            if(this.homepage.below==null)
                throw new PageNotFoundException();
            page = this.checksubpages(name, this.homepage);
            if (page == null) {
                subpage = this.homepage.below;
                while (page==null) {
                    page = this.checksubpages(name, subpage);
                    if (page == null) {
                        subpage = subpage.across;
                        if (subpage == null) {
                            throw new PageNotFoundException();
                        }
                    }
                }
            }
        }
        this.currentpage=page;
    }

    private PageNode checksubpages(String name, PageNode page) {
        page=page.below;
        Boolean searching=page!=null;
        while (searching) {
            if (name.equalsIgnoreCase(page.name)) {
                searching = false;
            } else {
                page = page.across;
                if (page == null) {
                    searching = false;
                }
            }
        }
        return page;
    }

    public String getCurrentPage() {
        String currentpageDetails = new String();
        currentpageDetails += this.currentpage.name;
        if (this.currentpage.above != null) {
            currentpageDetails += " is " + this.currentpage.above.name + "'s sub page. " + '\n';
        } else {
            currentpageDetails += "\n";
        }
        currentpageDetails += this.getPage(this.currentpage);
        return currentpageDetails;
    }
    
    
    private PageNode FindPage(String PageName){
    PageNode node = currentpage.below;
    while(node != null){
        if (node.name.equals(PageName)){
            return node;
        }
        node = node.across;
    }
    return null;
    
    }
    
        public void moveDown(String pageName){
        if (currentpage.below == null){
        System.out.println("no pages to navigate down to");
        return;
        }
        
        PageNode nextPage =FindPage(pageName.trim().toLowerCase());
        if (nextPage == null){
        System.out.println("no page found with name " + pageName);
        }else{
        currentpage = nextPage;
        System.out.println("moved to page " + currentpage.name);
        }
    }
    
    public void moveUp(){
        if (currentpage == homepage){
            System.out.println("already at home page");
            return;
        }
        currentpage = currentpage.above;
        System.out.println("move to page " + currentpage.name);
    }
    

}

