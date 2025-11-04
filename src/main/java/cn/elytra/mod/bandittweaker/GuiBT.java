package cn.elytra.mod.bandittweaker;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.Minecraft;

public class GuiBT extends GuiScreen {
    private GuiTextField chunkField, maxField, speedField;
    private GuiButton applyChunk, applyMax, applySpeed, closeBtn;

    @Override
    public void initGui() {
        int cx = this.width / 2;
        int cy = this.height / 2;
        this.chunkField = new GuiTextField(0, this.fontRenderer, cx - 100, cy - 40, 200, 20);
        this.chunkField.setText("64");
        this.maxField = new GuiTextField(1, this.fontRenderer, cx - 100, cy - 10, 200, 20);
        this.maxField.setText("1024");
        this.speedField = new GuiTextField(2, this.fontRenderer, cx - 100, cy + 20, 200, 20);
        this.speedField.setText("4");

        this.buttonList.clear();
        this.applyChunk = new GuiButton(10, cx - 120, cy - 40, 50, 20, "Set"); // applies chunk
        this.applyMax = new GuiButton(11, cx - 120, cy - 10, 50, 20, "Set"); // applies max
        this.applySpeed = new GuiButton(12, cx - 120, cy + 20, 50, 20, "Set"); // applies speed
        this.closeBtn = new GuiButton(13, cx - 40, cy + 60, 80, 20, "Close");

        this.buttonList.add(this.applyChunk);
        this.buttonList.add(this.applyMax);
        this.buttonList.add(this.applySpeed);
        this.buttonList.add(this.closeBtn);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.applyChunk) {
            String v = this.chunkField.getText();
            Minecraft.getMinecraft().player.sendChatMessage("/bt chunk " + v);
        } else if (button == this.applyMax) {
            String v = this.maxField.getText();
            Minecraft.getMinecraft().player.sendChatMessage("/bt max " + v);
        } else if (button == this.applySpeed) {
            String v = this.speedField.getText();
            Minecraft.getMinecraft().player.sendChatMessage("/bt speed " + v);
        } else if (button == this.closeBtn) {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int cx = this.width / 2;
        int cy = this.height / 2;
        drawCenteredString(this.fontRenderer, "Bandit Tweaker GUI (press K to open)", cx, cy - 80, 0xFFFFFF);
        drawString(this.fontRenderer, "Chunk size:", cx - 100, cy - 50, 0xFFFFFF);
        drawString(this.fontRenderer, "Max size:", cx - 100, cy - 20, 0xFFFFFF);
        drawString(this.fontRenderer, "Executor limit:", cx - 100, cy + 10, 0xFFFFFF);
        this.chunkField.drawTextBox();
        this.maxField.drawTextBox();
        this.speedField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        this.chunkField.textboxKeyTyped(typedChar, keyCode);
        this.maxField.textboxKeyTyped(typedChar, keyCode);
        this.speedField.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.chunkField.mouseClicked(mouseX, mouseY, mouseButton);
        this.maxField.mouseClicked(mouseX, mouseY, mouseButton);
        this.speedField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
