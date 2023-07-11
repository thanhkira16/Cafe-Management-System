package controller;

import javax.swing.JFrame;
import model.User;

import view.Admin;
import view.ChangePassword;
import view.ChangeSecurityQuestion;
import view.ForgotPassword;
import view.Home;
import view.Login;
import view.ManageCategory;
import view.NewProduct;
import view.PlaceOrder;
import view.Settings;
import view.Signup;
import view.VerifyUser;
import view.ViewBillOrderPlacedDetails;
import view.ViewEditDeleteProduct;

public class Client {

    public static void openView(View viewName, String email) {
        if (viewName != null) {
            switch (viewName) {
                case HOME:
                    homePageFrm = new Home(email);
                    homePageFrm.setVisible(true);
                    break;
            }
        }
        

    }
    public enum View {
        LOGIN,
        SIGNUP,
        HOME,
        MANAGE_CATEGORY,
        NEW_PRODUCT,
        PLACE_ORDER,
        SETTINGS,
        ADMIN,
        VERIFY_USER,
        CHANGE_PASSWORD,
        CHANGE_SECURITY_QUESTION,
        FORGOT_PASSWORD,
        VILL_BILL_ORDER_PLACED_DETAILS,
        VIEW_EDIT_DELETE_PRODUCT
    }

    public static User user;
    // Danh sách giao diện
    public static Login loginFrm;
    public static Signup registerFrm;
    public static Home homePageFrm;
    public static ManageCategory categoryFrm;
    public static NewProduct newProductFrm;
    public static PlaceOrder placeOrderFrm;
    public static Settings settingsFrm;
    public static Admin adminFrm;
    public static VerifyUser verifyUserFrm;
    public static ChangePassword changePasswordFrm;
    public static ChangeSecurityQuestion changeSecurityQuestionFrm;
    public static ForgotPassword forgotPasswordFrm;
    public static ViewBillOrderPlacedDetails orderDetailsFrm;
    public static ViewEditDeleteProduct viewEditDeleteProductFrm;
    // Thêm các giao diện còn lại tại đây

    // Thiết lập socket
    public static SocketHandle socketHandle;

    public Client() {
    }

    public static JFrame getVisibleJFrame() {
         if (categoryFrm != null && categoryFrm.isVisible())
            return categoryFrm;
        if (homePageFrm != null && homePageFrm.isVisible()) {
            return homePageFrm;
        }
        if (newProductFrm != null && newProductFrm.isVisible()) {
            return newProductFrm;
        }
        if (placeOrderFrm != null && placeOrderFrm.isVisible()) {
            return placeOrderFrm;
        }
        // Thêm các trường hợp còn lại tại đây
        return loginFrm;
    }

    // Thêm phương thức mở giao diện
    public static void openView(View viewName) {
        if (viewName != null) {
            switch (viewName) {
                case LOGIN:
                    loginFrm = new Login();
                    loginFrm.setVisible(true);
                    break;
                case SIGNUP:
                    registerFrm = new Signup();
                    registerFrm.setVisible(true);
                    break;
                
                case MANAGE_CATEGORY:
                    categoryFrm = new ManageCategory();
                    categoryFrm.setVisible(true);
                    break;
                case NEW_PRODUCT:
                    newProductFrm = new NewProduct();
                    newProductFrm.setVisible(true);
                    break;
                case PLACE_ORDER:
                    placeOrderFrm = new PlaceOrder();
                    placeOrderFrm.setVisible(true);
                    break;
                case SETTINGS:
                    settingsFrm = new Settings();
                    settingsFrm.setVisible(true);
                    break;
                case ADMIN:
                    adminFrm = new Admin();
                    adminFrm.setVisible(true);
                    break;
                case VERIFY_USER:
                    verifyUserFrm = new VerifyUser();
                    verifyUserFrm.setVisible(true);
                    break;
                case CHANGE_PASSWORD:
                    changePasswordFrm = new ChangePassword();
                    changePasswordFrm.setVisible(true);
                    break;
                case CHANGE_SECURITY_QUESTION:
                    changeSecurityQuestionFrm = new ChangeSecurityQuestion();
                    changeSecurityQuestionFrm.setVisible(true);
                    break;
                case FORGOT_PASSWORD:
                    forgotPasswordFrm = new ForgotPassword();
                    forgotPasswordFrm.setVisible(true);
                    break;
                case VILL_BILL_ORDER_PLACED_DETAILS:
                    orderDetailsFrm = new ViewBillOrderPlacedDetails();
                    orderDetailsFrm.setVisible(true);
                    break;
                case VIEW_EDIT_DELETE_PRODUCT:
                    viewEditDeleteProductFrm = new ViewEditDeleteProduct();
                    viewEditDeleteProductFrm.setVisible(true);
                    break;
                // Thêm các trường hợp còn lại tại đây
            }
        }
    }

    // Thêm phương thức đóng giao diện
    public static void closeView(View viewName) {
        if (viewName != null) {
            switch (viewName) {
                case LOGIN:
                    if (loginFrm != null)
                        loginFrm.dispose();
                    break;
                case SIGNUP:
                    if (registerFrm != null)
                        registerFrm.dispose();
                    break;
                case HOME:
                    if (homePageFrm != null)
                        homePageFrm.dispose();
                    break;
                case MANAGE_CATEGORY:
                    if (categoryFrm != null)
                        categoryFrm.dispose();
                    break;
                case NEW_PRODUCT:
                    if (newProductFrm != null)
                        newProductFrm.dispose();
                    break;
                case PLACE_ORDER:
                    if (placeOrderFrm != null)
                        placeOrderFrm.dispose();
                    break;
                case SETTINGS:
                    if (settingsFrm != null)
                        settingsFrm.dispose();
                    break;
                case ADMIN:
                    if (adminFrm != null)
                        adminFrm.dispose();
                    break;
                case VERIFY_USER:
                    if (verifyUserFrm != null)
                        verifyUserFrm.dispose();
                    break;
                case CHANGE_PASSWORD:
                    if (changePasswordFrm != null)
                        changePasswordFrm.dispose();
                    break;
                case CHANGE_SECURITY_QUESTION:
                    if (changeSecurityQuestionFrm != null)
                        changeSecurityQuestionFrm.dispose();
                    break;
                case FORGOT_PASSWORD:
                    if (forgotPasswordFrm != null)
                        forgotPasswordFrm.dispose();
                    break;
                case VILL_BILL_ORDER_PLACED_DETAILS:
                    if (orderDetailsFrm != null)
                        orderDetailsFrm.dispose();
                    break;
                case VIEW_EDIT_DELETE_PRODUCT:
                    if (viewEditDeleteProductFrm != null)
                        viewEditDeleteProductFrm.dispose();
                    break;
                // Thêm các trường hợp còn lại tại đây
            }

        }
    }

    // Thêm phương thức khởi tạo giao diện
    public void initView() {
        loginFrm = new Login();
        loginFrm.setVisible(true);
        socketHandle = new SocketHandle();
        socketHandle.run();
    }

    // Thêm phương thức đóng tất cả giao diện
    public static void closeAllViews() {
        closeView(View.LOGIN);
        closeView(View.SIGNUP);
        closeView(View.HOME);
        closeView(View.MANAGE_CATEGORY);
        closeView(View.NEW_PRODUCT);
        closeView(View.PLACE_ORDER);
        closeView(View.SETTINGS);
        closeView(View.ADMIN);
        closeView(View.VERIFY_USER);
        closeView(View.CHANGE_PASSWORD);
        closeView(View.CHANGE_SECURITY_QUESTION);
        closeView(View.FORGOT_PASSWORD);
        closeView(View.VILL_BILL_ORDER_PLACED_DETAILS);
        closeView(View.VIEW_EDIT_DELETE_PRODUCT);
        // Thêm các trường hợp còn lại tại đây
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.initView();
    }
}
