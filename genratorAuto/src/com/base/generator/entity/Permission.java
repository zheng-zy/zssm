package com.base.generator.entity;

public class Permission {
    private Integer id;

    private String permissionname;

    private String permissionsign;

    private String permissiondesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    public String getPermissionsign() {
        return permissionsign;
    }

    public void setPermissionsign(String permissionsign) {
        this.permissionsign = permissionsign == null ? null : permissionsign.trim();
    }

    public String getPermissiondesc() {
        return permissiondesc;
    }

    public void setPermissiondesc(String permissiondesc) {
        this.permissiondesc = permissiondesc == null ? null : permissiondesc.trim();
    }
}