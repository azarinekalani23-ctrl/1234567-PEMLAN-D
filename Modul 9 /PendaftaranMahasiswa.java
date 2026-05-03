import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PendaftaranMahasiswa extends JFrame {
    private JTextField txtNama, txtTglLahir, txtNoPendaftaran, txtNoTelp, txtAlamat, txtEmail;
    private JButton btnSubmit;

    public PendaftaranMahasiswa() {
        // --- 1. Pengaturan Jendela Utama ---
        setTitle("Form Daftar Ulang Mahasiswa Baru");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 2. Panel Header ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(230, 230, 250)); // Warna biru muda lembut
        JLabel lblHeader = new JLabel("PENDAFTARAN MAHASISWA BARU");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 14));
        panelHeader.add(lblHeader);
        add(panelHeader, BorderLayout.NORTH);

        // --- 3. Panel Form Input ---
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inisialisasi Field
        txtNama = new JTextField(20);
        txtTglLahir = new JTextField(20);
        txtNoPendaftaran = new JTextField(20);
        txtNoTelp = new JTextField(20);
        txtAlamat = new JTextField(20);
        txtEmail = new JTextField(20);

        String[] labelTexts = {
            "Nama Lengkap", "Tanggal Lahir", "Nomor Pendaftaran", 
            "No. Telp", "Alamat", "E-mail"
        };
        JTextField[] fields = {
            txtNama, txtTglLahir, txtNoPendaftaran, 
            txtNoTelp, txtAlamat, txtEmail
        };

        for (int i = 0; i < labelTexts.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            panelForm.add(new JLabel(labelTexts[i]), gbc);
            
            gbc.gridx = 1;
            panelForm.add(fields[i], gbc);
        }
        add(panelForm, BorderLayout.CENTER);

        // --- 4. Tombol Submit ---
        btnSubmit = new JButton("Submit");
        btnSubmit.setPreferredSize(new Dimension(100, 30));
        JPanel panelButton = new JPanel();
        panelButton.add(btnSubmit);
        add(panelButton, BorderLayout.SOUTH);

        // --- 5. Logika Program ---
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validasi: Cek apakah ada yang kosong
                if (isAdaFieldKosong()) {
                    JOptionPane.showMessageDialog(null, 
                        "Semua kolom harus terisi!", "Peringatan", 
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    // Dialog Konfirmasi
                    int confirm = JOptionPane.showConfirmDialog(null, 
                        "Apakah anda yakin data yang Anda isi sudah benar?", 
                        "Konfirmasi Data", 
                        JOptionPane.OK_CANCEL_OPTION);

                    if (confirm == JOptionPane.OK_OPTION) {
                        tampilkanDataBaru();
                    }
                }
            }
        });
    
    private boolean isAdaFieldKosong() {
        return txtNama.getText().trim().isEmpty() || 
               txtTglLahir.getText().trim().isEmpty() ||
               txtNoPendaftaran.getText().trim().isEmpty() ||
               txtNoTelp.getText().trim().isEmpty() ||
               txtAlamat.getText().trim().isEmpty() ||
               txtEmail.getText().trim().isEmpty();
    }

    private void tampilkanDataBaru() {
        JFrame frameData = new JFrame("Data Mahasiswa");
        frameData.setSize(400, 350);
        frameData.setLocationRelativeTo(null);
        
        JTextArea areaData = new JTextArea();
        areaData.setEditable(false);
        areaData.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String konten = "          Data Mahasiswa\n" +
                        "==============================\n\n" +
                        "Nama           : " + txtNama.getText() + "\n" +
                        "Tanggal Lahir  : " + txtTglLahir.getText() + "\n" +
                        "No.Pendaftaran : " + txtNoPendaftaran.getText() + "\n" +
                        "No.Telp        : " + txtNoTelp.getText() + "\n" +
                        "Alamat         : " + txtAlamat.getText() + "\n" +
                        "E-mail         : " + txtEmail.getText() + "\n\n" +
                        "==============================";

        areaData.setText(konten);
        frameData.add(new JScrollPane(areaData));
        frameData.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PendaftaranMahasiswa().setVisible(true);
        });
    }
}
