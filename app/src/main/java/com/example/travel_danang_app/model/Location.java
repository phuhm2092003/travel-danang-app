package com.example.travel_danang_app.model;

public class Location {
    private int id;
    private String hinhAnh;
    private String gioMoCua;
    private String tenDiaDiem;
    private String viTri;
    private String giaVe;
    private String khuyenNghi;
    private String gioiThieu;
    private String hienThi;
    private int isFavourite;

    // Constructor
    public Location(int id, String hinhAnh, String gioMoCua, String tenDiaDiem, String viTri, String giaVe,
                    String khuyenNghi, String gioiThieu, String hienThi, int isFavourite) {
        this.id = id;
        this.hinhAnh = hinhAnh;
        this.gioMoCua = gioMoCua;
        this.tenDiaDiem = tenDiaDiem;
        this.viTri = viTri;
        this.giaVe = giaVe;
        this.khuyenNghi = khuyenNghi;
        this.gioiThieu = gioiThieu;
        this.hienThi = hienThi;
        this.isFavourite = isFavourite;
    }

    // Getters and Setters (Optional)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getGioMoCua() {
        return gioMoCua;
    }

    public void setGioMoCua(String gioMoCua) {
        this.gioMoCua = gioMoCua;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(String giaVe) {
        this.giaVe = giaVe;
    }

    public String getKhuyenNghi() {
        return khuyenNghi;
    }

    public void setKhuyenNghi(String khuyenNghi) {
        this.khuyenNghi = khuyenNghi;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getHienThi() {
        return hienThi;
    }

    public void setHienThi(String hienThi) {
        this.hienThi = hienThi;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", gioMoCua='" + gioMoCua + '\'' +
                ", tenDiaDiem='" + tenDiaDiem + '\'' +
                ", viTri='" + viTri + '\'' +
                ", giaVe='" + giaVe + '\'' +
                ", khuyenNghi='" + khuyenNghi + '\'' +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", hienThi='" + hienThi + '\'' +
                ", isFavourite=" + isFavourite +
                '}';
    }
}
